process.env.NODE_ENV = 'test';
process.env.MONGO_DB_URL = 'mongodb://localhost:27017/example-test';

const chai = require('chai');
const chaiHttp = require('chai-http');
const server = require('../app');
const Post = require('../models/Post');
const should = chai.should();

chai.use(chaiHttp);


describe('Task', () => {
    before('Check server is running', done => server.started ? done() : server.on( "started", () => done()));

    let agent;
    beforeEach('Load details', async function() {
        agent = chai.request.agent(server);
    })
    describe('/GET posts', () => {
        it('list should be empty', async () => {
            const listRes = await agent.get(`/posts`).send();
            listRes.should.have.status(200);
            listRes.body.should.be.a('array');
            listRes.body.should.have.lengthOf(0);
        })
        it('add and list', async () => {
            const title = "Post 1";
            const content = "Lorem ipsum";
            const addRes = await agent.post(`/posts`).send({"title":title, "content":content});
            addRes.should.have.status(200);
            addRes.body.title.should.be.eql(title);
            addRes.body.content.should.be.eql(content);
            const listRes = await agent.get(`/posts`).send();
            listRes.should.have.status(200);
            listRes.body.should.be.a('array');
            listRes.body.should.have.lengthOf(1);
            listRes.body[0].title.should.be.eql(addRes.body.title);
            listRes.body[0].content.should.be.eql(addRes.body.content);
        })
        it('add multi', async () => {
            await agent.post(`/posts`).send({"title": "Post 1", "content": "Lorem ipsum"});
            await agent.post(`/posts`).send({"title": "Post 2", "content": "Lorem ipsum"});
            await agent.post(`/posts`).send({"title": "Post 3", "content": "Lorem ipsum"});

            const listRes = await agent.get(`/posts`).send();
            listRes.should.have.status(200);
            listRes.body.should.be.a('array');
            listRes.body.should.have.lengthOf(3);
        })
    })

    describe('/GET posts/:id', () => {
        it('list should be empty', async () => {
            const title = "Post 1";
            const content = "Lorem ipsum";
            const addRes = await agent.post(`/posts`).send({"title":title, "content":content});
            addRes.should.have.status(200);
            const singleRes = await agent.get(`/posts/${addRes.body._id}`).send();
            singleRes.should.have.status(200);
            singleRes.body.should.be.a('object');
            singleRes.body.title.should.be.eql(title);
            singleRes.body.content.should.be.eql(content);
        })
    })

    describe('/PATCH posts/:id', () => {
        it('should update post', async () => {
            const title = "Post 1";
            const titleAfter = "Post 2";
            const content = "Lorem ipsum";
            const addRes = await agent.post(`/posts`).send({"title":title, "content":content});
            addRes.should.have.status(200);
            const singleRes1 = await agent.get(`/posts/${addRes.body._id}`).send();
            singleRes1.should.have.status(200);
            singleRes1.body.title.should.be.eql(title);
            const updateRes = await agent.patch(`/posts/${addRes.body._id}`).send({"title": titleAfter});
            updateRes.should.have.status(200);
            const singleRes2 = await agent.get(`/posts/${addRes.body._id}`).send();
            singleRes2.should.have.status(200);
            singleRes2.body.title.should.be.eql(titleAfter);
            singleRes2.body.content.should.be.eql(content);
        })
    })

    describe('/DELETE posts/:id', () => {
        it('should remove post', async () => {
            const title = "Post 1";
            const content = "Lorem ipsum";
            const addRes = await agent.post(`/posts`).send({"title":title, "content":content});
            addRes.should.have.status(200);
            const singleRes1 = await agent.get(`/posts/${addRes.body._id}`).send();
            singleRes1.should.have.status(200);
            const deleteRes = await agent.delete(`/posts/${addRes.body._id}`).send();
            deleteRes.should.have.status(204);
            const singleRes2 = await agent.get(`/posts/${addRes.body._id}`).send();
            singleRes2.should.have.status(404);
        })
    })

    afterEach('Clean up', async () => {
        await Post.deleteMany();
    })
});
