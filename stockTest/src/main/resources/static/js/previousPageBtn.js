new Vue({
    el: '#previousPageBtn',
    template:
        '<button class="page_btn" id="previous_page" v-on:click="previousPage">' +
        'previous' +
        '</button>',
    methods:{
        previousPage: function () {

            var params = {
                category: catName,
                limit: limit,
                sort: sort,
            }

            console.log(params)

            sortedProducts = Array()
            this.$http.get('/product/previousPage', {params: params}).then(
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
});