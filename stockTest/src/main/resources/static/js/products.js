var productAPI = Vue.resource('/product');

Vue.component('product-row', {
    props: ['product'],
    template: '<tr>' +
        '<td>{{product.id}}</td>' +
        '<td>{{product.name}}</td>' +
        '<td>{{product.category.name}}</td>' +
        '<td>{{product.description}}</td>' +
        '<td>{{product.imageResource}}</td>' +
        '<td>{{product.price}}</td>' +
        '<td>{{product.count}}</td>' +
        '</tr>'
});

Vue.component('products-list', {
    props: ['products'],
    template: '<table>' +
            '<th>product id</th>' +
            '<th>name</th>' +
            '<th>category</th>' +
            '<th>description</th>' +
            '<th>image</th>' +
            '<th>price</th>' +
            '<th>count</th>' +
            '<product-row v-for="product in products" :key="product.id" :product="product"/>' +
        '</table>',
    created: function(){
        /*productAPI.get().then(result =>
            result.json().then(data =>
                data.forEach(product => this.products.push(product))
            )
        )*/

        catName = "all"
        var params = {
            category: catName,
            limit: limit,
            sort: sort
        }
        this.$http.get('/product/page', {params: params}).then(
            result => result.json().then(
                data => data.forEach(
                    product => this.products.push(product)
                )
            )
        );

    }
});

var table = new Vue({
        el: '#products-table',
        template: '<products-list :products="products" />',
        data: {
            products: [],
        },
    }
);