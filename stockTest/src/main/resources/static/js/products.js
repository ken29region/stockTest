var productImg;
var imageDir = "/home/ubuntu/images";

Vue.component('product-row', {
    props: ['product'],
    data: function(){
      return{
          image: null
    }
    },
    template: '<tr>' +
        '<td>{{product.id}}</td>' +
        '<td>{{product.name}}</td>' +
        '<td>{{product.category.name}}</td>' +
        '<td>{{product.description}}</td>' +
        '<td class="productImgTd"><img class="productImg" :src="getPicture()"></td>' +
        '<td>{{product.price}}</td>' +
        '<td>{{product.count}}</td>' +
        '</tr>',

    methods:{
        getPicture() {

            productImg = imageDir + "\\" + this.product.imageSrc;
            console.log(productImg)
            if(productImg == 'no image')
                return imageDir + "\\" + "noImage.png"

            return productImg
        },
    },

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