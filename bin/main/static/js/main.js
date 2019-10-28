var messageApi = Vue.resource('/message{/id}');

Vue.component('message-add', {
    data() {
        return {
            message: ''
        }
    },
    props: ['messages'],
    template: '<div>' +
        '<input v-model="message">' +
        '<button @click="storeMessage">Save</button>' +
        '</div>',
    methods: {
        storeMessage() {
            messageApi.save({ text: this.message }).then(result =>
                result.json().then(data => {
                    this.messages.push(data);
                    this.message = '';
                })
            );
        }
    }
});

Vue.component('message-row', {
    props: ['message'],
    template: '<div>' +
        '<i>{{message.id}}</i> {{message.text}}' +
        '<span>' +
        '<input type="button" value="Edit" @click="edit">' +
        '</span>' +
        '</div>',
    methods: {
        edit() {}
    }
});

Vue.component('message-list', {
    props: ['messages'],
    template: '<div>' +
        '<message-add :messages="messages"></message-add>' +
        '<message-row v-for="message in messages" :key="message.id" :message="message"></message-row>' +
        '</div>',
    created: function() {
        messageApi.get().then(result => {
            result.json().then(data =>
                data.forEach(message => {
                    this.messages.push(message);
                }));
        });
    }
});

var app = new Vue({
    el: '#app',
    template: '<message-list :messages="messages"></message-list>',
    data() {
        return {
            messages: []
        }
    }
});