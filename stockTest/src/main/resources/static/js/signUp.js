Vue.component('user-row', {
    props: ['user'],
    template: '<tr>{{ user.id }} {{ user.first_name }}</tr>'
})

Vue.component('users-list', {
    props: ['users'],
    template:
        '<table>' +
            '<user-row v-for="user in users" :key="user.id" :user="user"/>' +
        '</table>'
})

new Vue({
    el: '#users_table',
    template: '<users-list :users="users"/>',
    data:{
        users: [
        ]
    }
})