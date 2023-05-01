
import collections
from collections import defaultdict, Counter

class Sent:
    """A PL sentence with an operator and 0 or more arguments.
    op is a str like '+' or 'sin'; args are Sentences.
    Sent('x') or Symbol('x') creates a symbol (a nullary Sent).
    Sent('-', x) creates a unary; Sent('+', x, 1) creates a binary."""

    def __init__(self, op, *args):
        self.op = str(op)
        self.args = args

    # Operator overloads
    def __neg__(self):
        return Sent('-', self)

    def __pos__(self):
        return Sent('+', self)

    def __invert__(self):
        return Sent('~', self)

    def __add__(self, rhs):
        return Sent('+', self, rhs)

    def __sub__(self, rhs):
        return Sent('-', self, rhs)

    def __mul__(self, rhs):
        return Sent('*', self, rhs)

    def __pow__(self, rhs):
        return Sent('**', self, rhs)

    def __mod__(self, rhs):
        return Sent('%', self, rhs)

    def __and__(self, rhs):
        return Sent('&', self, rhs)

    def __xor__(self, rhs):
        return Sent('^', self, rhs)

    def __rshift__(self, rhs):
        return Sent('>>', self, rhs)

    def __lshift__(self, rhs):
        return Sent('<<', self, rhs)

    def __truediv__(self, rhs):
        return Sent('/', self, rhs)

    def __floordiv__(self, rhs):
        return Sent('//', self, rhs)

    def __matmul__(self, rhs):
        return Sent('@', self, rhs)

    def __or__(self, rhs):
        """Allow both P | Q, and P |'==>'| Q."""
        if isinstance(rhs, Sentence):
            return Sent('|', self, rhs)
        else:
            return PartialSent(rhs, self)

    # Reverse operator overloads
    def __radd__(self, lhs):
        return Sent('+', lhs, self)

    def __rsub__(self, lhs):
        return Sent('-', lhs, self)

    def __rmul__(self, lhs):
        return Sent('*', lhs, self)

    def __rdiv__(self, lhs):
        return Sent('/', lhs, self)

    def __rpow__(self, lhs):
        return Sent('**', lhs, self)

    def __rmod__(self, lhs):
        return Sent('%', lhs, self)

    def __rand__(self, lhs):
        return Sent('&', lhs, self)

    def __rxor__(self, lhs):
        return Sent('^', lhs, self)

    def __ror__(self, lhs):
        return Sent('|', lhs, self)

    def __rrshift__(self, lhs):
        return Sent('>>', lhs, self)

    def __rlshift__(self, lhs):
        return Sent('<<', lhs, self)

    def __rtruediv__(self, lhs):
        return Sent('/', lhs, self)

    def __rfloordiv__(self, lhs):
        return Sent('//', lhs, self)

    def __rmatmul__(self, lhs):
        return Sent('@', lhs, self)

    def __call__(self, *args):
        """Call: if 'f' is a Symbol, then f(0) == Sent('f', 0)."""
        if self.args:
            raise ValueError('Can only do a call for a Symbol, not an Sent')
        else:
            return Sent(self.op, *args)

    # Equality and repr
    def __eq__(self, other):
        """x == y' evaluates to True or False; does not build an Sent."""
        return isinstance(other, Sent) and self.op == other.op and self.args == other.args

    def __lt__(self, other):
        return isinstance(other, Sent) and str(self) < str(other)

    def __hash__(self):
        return hash(self.op) ^ hash(self.args)

    def __repr__(self):
        op = self.op
        args = [str(arg) for arg in self.args]
        if op.isidentifier():  # f(x) or f(x, y)
            return '{}({})'.format(op, ', '.join(args)) if args else op
        elif len(args) == 1:  # -x or -(x + 1)
            return op + args[0]
        else:  # (x - y)
            opp = (' ' + op + ' ')
            return '(' + opp.join(args) + ')'


Number = (int, float, complex)
Sentence = (Sent, Number)


def Symbol(name):
    """A Symbol is just an Sent with no args."""
    return Sent(name)


def symbols(names):
    """Return a tuple of Symbols; names is a comma/whitespace delimited str."""
    return tuple(Symbol(name) for name in names.replace(',', ' ').split())


def subsentences(x):
    """Yield the subsentences of an Sentence (including x itself)."""
    yield x
    if isinstance(x, Sent):
        for arg in x.args:
            yield from subsentences(arg)


class PartialSent:
    """Given 'P |'==>'| Q, first form PartialSent('==>', P), then combine with Q."""

    def __init__(self, op, lhs):
        self.op, self.lhs = op, lhs

    def __or__(self, rhs):
        return Sent(self.op, self.lhs, rhs)

    def __repr__(self):
        return "PartialSent('{}', {})".format(self.op, self.lhs)


def sent(x):
    """Shortcut to create an Sentence. x is a str in which:
    - identifiers are automatically defined as Symbols.
    - ==> is treated as an infix |'==>'|, as are <== and <=>.
    If x is already an Sentence, it is returned unchanged. Example:
    >>> sent('P & Q ==> Q')
    ((P & Q) ==> Q)
    """
    return eval(sent_handle_infix_ops(x), defaultkeydict(Symbol)) if isinstance(x, str) else x


infix_ops = '==> <== <=>'.split()


def sent_handle_infix_ops(x):
    """Given a str, return a new str with ==> replaced by |'==>'|, etc.
    >>> sent_handle_infix_ops('P ==> Q')
    "P |'==>'| Q"
    """
    for op in infix_ops:
        x = x.replace(op, '|' + repr(op) + '|')
    return x


class defaultkeydict(collections.defaultdict):
    """Like defaultdict, but the default_factory is a function of the key.
    >>> d = defaultkeydict(len); d['four']
    4
    """

    def __missing__(self, key):
        self[key] = result = self.default_factory(key)
        return result

