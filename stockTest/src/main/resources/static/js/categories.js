
var categoryAPI = Vue.resource('/category{/id}');

Vue.component('category-row', {
    data: function(){
      return{
          show: true
      }
    },
    props: ['category'],
    template:
        '<tr :id="category.id">' +
            '<td><transition appear-class="custom-appear-class">' +
                    '<div ref="cat_name" v-if="show">{{category.name}}</div>' +
                    '<input ref="input_name" class="cat_name" v-if="!show" placeholder="insert name"/>' +
            '</transition></td>' +
            '<td><transition appear-class="custom-appear-class">' +
                    '<div ref="cat_descr" v-if="show">{{category.description}}</div>' +
                    '<textarea ref="input_description" class="cat_descr" placeholder="insert description" v-if="!show"></textarea>' +
            '</transition></td>' +
            '<td>{{category.count}}</td>' +
            '<td><span>' +
                    '<input class="editBtn" type="button" value="Edit" @click="isShow"/>' +
                    '<input class="editBtn" type="button" value="Delete" @click="deleteCategory"/>' +
                    '<transition appear-class="custom-appear-class">' +
                        '<input class="editBtn" type="button" value="Add" v-if="!show" @click="addCategory"/>' +
                    '</transition>' +
                '</span></td>' +
        '</tr>',
    methods: {
        isShow: function(){
            this.show = !this.show
        },
        deleteCategory: function(){

            var item = {
                id: this.category.id,
                name: this.category.name,
                description: this.category.description,
                count: this.category.count
            }
            categoryAPI.delete({id: item.id}, item)
        },
        addCategory: function(){

            var name = this.$refs.input_name.value
            var description = this.$refs.input_description.value

            var item = {
                id: this.category.id,
                name: name,
                description: description,
                count: this.category.count
            }

            console.log(item)
            categoryAPI.update({id: item.id}, item)
        }
    },
});

var catTable = Vue.component('category-table',{
    props: ['categories'],
    template:
        '<table id="categories_table">' +
            '<th>Name</th><th>Description</th><th>Count</th><th>Action</th>' +
            '<category-row v-for="category in categories" :key="category.id" :category="category"/>' +
        '</table>',
});

var cat = new Vue({

    el: '#categories_div',
    data:{
        categories: [],
    },
    created: function(){
        categoryAPI.get().then(result =>
            result.json().then(data =>
                data.forEach(category => this.categories.push(category))
            )
        )
    },
});
