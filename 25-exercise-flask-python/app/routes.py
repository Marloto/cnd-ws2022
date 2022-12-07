from app import app

import os

killed = False

@app.route('/kill')
def kill():
    global killed
    killed = True
    return '', 204

@app.route('/healthz')
def healthz():
    if killed:
        return '', 500
    return '', 204

@app.route('/')
@app.route('/hello')
def hello():
    greeting = os.environ.get('GREETING')
    if greeting != None:
        return "Hello, " + greeting + "!"
    return "Hello, World!"