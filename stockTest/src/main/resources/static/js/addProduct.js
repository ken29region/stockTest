var productAPI = Vue.resource('/product{/id}');

var addProduct = new Vue({
    el: '#add_form',
    data: {
        products: [],
    },
    methods:{
        saveBtn(){
            var pName = this.$refs.nameIn.value;
            var categoryName = catName;
            var pDescr = this.$refs.descriptionIn.value;
            var pImage = this.$refs.imageIn.value;
            var pPrice = this.$refs.priceIn.value;

            var product = {
                name: pName,
                category: {name : categoryName},
                description: pDescr,
                imageResource: pImage,
                price: pPrice
            };

            console.log(product);
            productAPI.save({}, product);
        },
        handleChange(e){}
    }
});