from twilio.rest import Client

# Your Account Sid and Auth Token from twilio.com/console

account_sid = 'account_ID'
auth_token = 'auth_TOKEN'
client = Client(account_sid, auth_token)

i = 0
while i < 100:
    message = client.messages \
        .create(
             body='hi', # whatever message you want to send
            from_='+18449234096',
            to='+18653099416'
        )
    print(message.sid)
    i += 1