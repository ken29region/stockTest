var sort = 'id';
var sortedProducts;

Vue.component('sort_select',{
    props: ['sortElement'],
    template:
        '<select id="sort_elements" ref="sort_elements" v-model="sortElement" v-on:change="getProducts">' +
        '   <option>id</option>' +
        '   <option>name</option>' +
        '   <option>price desc</option>' +
        '   <option>price asc</option>' +
        '</select>',
    methods:{
        getProducts: function () {
            sort = this.sortElement
            var params = {
                category: catName,
                limit: limit,
                sort: sort
            }

            console.log(params)

            sortedProducts = Array()
            this.$http.get('/product/page', {params: params}).then(
                result => result.json().then(
                    data => data.forEach(
                        product => sortedProducts.push(product)
                    )
                )
            );
            table.products = sortedProducts
            console.log(sortedProducts)
        }
    }
})

new Vue({
    data:{
        sortElement: 'id',
    },
    el: '#sortElementDiv',
    template: '<sort_select/>'
})