var limit;
var displayProducts;

Vue.component('displayElement_select',{
    props: ['selected'],
    template:
        '<select id="display_elements" ref="display_elements" v-model="selected" v-on:change="getProducts">' +
        '   <option>5</option>' +
        '   <option>10</option>' +
        '   <option>20</option>' +
        '   <option>40</option>' +
        '</select>',
    methods: {
        getProducts: function () {
            limit = this.selected
            var params = {
                category: catName,
                limit: limit,
                sort: sort
            }

            console.log(params)

            displayProducts = Array()
            this.$http.get('/product/page', {params: params}).then(
                result => result.json().then(
                    data => data.forEach(
                        product => displayProducts.push(product)
                    )
                )
            );
            table.products = displayProducts
            console.log(displayProducts)
        }
    }
})

new Vue({
    data:{
      selected: '10',
    },
    el: '#displayElementsDiv',
    template: '<displayElement_select/>'
})