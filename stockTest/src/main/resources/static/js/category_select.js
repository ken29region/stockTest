var catName;
var selectedProducts;

var categoryAPI = Vue.resource("/category{/id}", {}, {method: 'GET', url: '/category{/id}'});
var catProductsAPI = Vue.resource("/product/catName", {}, {method: 'GET', url: '/product/catName'})

var cat = Vue.component('option-category', {
    props: ['category'],
    template: '<option>{{category.name}}</option>'
});

var cat_s = Vue.component('select-category', {
    props:
        ['categories'],
    template:
        '<select id="category_select" ref="category_select" v-on:change="handleChange" align="center">' +
        '<option>all</option>' +
        '<option-category v-for="category in categories" :key="category.id" :category="category"/>' +
        '</select>',
    created: function(){
        categoryAPI.get().then(result =>
        result.json().then(data =>
        data.forEach(category => this.categories.push(category))
    )
    )},
    methods:{
        handleChange: function (e) {
            if(e.target.options.selectedIndex > -1){
                catName = e.target.options[e.target.options.selectedIndex].value
                selectedProducts = new Array()
                var params = {
                    category: catName,
                    limit: limit,
                    sort: sort
                }
                this.$http.get('/product/page', {params: params}).then(
                    result => result.json().then(
                        data => data.forEach(
                            product => selectedProducts.push(product)
                        )
                    )
                );
                table.products = selectedProducts
                console.log(selectedProducts)
            }
        }
    }
});

var app = new Vue({
    el: '#category_select',
    template: '<select-category :categories="categories"/>',
    data: {
        categories: [],
    }
});