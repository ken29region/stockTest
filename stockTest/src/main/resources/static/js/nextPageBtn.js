
new Vue({
    el: '#nextPageBtn',
    template:
        '<button class="page_btn" id="next_page" v-on:click="nextPage">' +
        'next' +
        '</button>',
    methods:{
        nextPage: function () {

            var params = {
                category: catName,
                limit: limit,
                sort: sort,
            }

            console.log(params)

            sortedProducts = Array()
            this.$http.get('/product/nextPage', {params: params}).then(
                result => result.json().then(
                    data => data.forEach(
                        product => sortedProducts.push(product)
                    )
                )
            );
            table.products = sortedProducts
            console.log(sortedProducts)
        },
    }
});