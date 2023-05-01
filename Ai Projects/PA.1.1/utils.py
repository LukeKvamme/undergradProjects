
from sent import *

def extend(s, var, val):
    """Copy dict s and extend it by setting var to val; return copy."""
    return {**s, var: val}

def is_symbol(s):
    """A string s is a symbol if it starts with an alphabetic char.
    >>> is_symbol('R2D2')
    True
    """
    return isinstance(s, str) and s[:1].isalpha()

def is_prop_symbol(s):
    """A proposition logic symbol is an initial-uppercase string.
    >>> is_prop_symbol('exe')
    False
    """
    return is_symbol(s) and s[0].isupper()

def prop_symbols(x):
    """Return the set of all propositional symbols in x."""
    if not isinstance(x, Sent):
        return set()
    elif is_prop_symbol(x.op):
        return {x}
    else:
        return {symbol for arg in x.args for symbol in prop_symbols(arg)}