ORIGINAL = 'test.ephesoft.kumulus.sg'
ORIGINAL_PORT = 8080
PROXIED = 'localhost'
PROXIED_PORT = 80
USE_SSL = False
STATE_DB = 'state.shelve'

def fq_original():
    return '%s:%s' % (ORIGINAL, ORIGINAL_PORT)

def fq_proxied():
    return '%s:%s' % (PROXIED, PROXIED_PORT)
