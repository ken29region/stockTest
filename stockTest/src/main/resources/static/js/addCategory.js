
Vue.component('modal',{

    template:
        '<transition name="modal">' +
        ' <div class="modal-mask">\n' +
        '      <div class="modal-wrapper">\n' +
        '           <div class="modal-container">' +
                        '<input ref="input_name" type="text" placeholder="insert name" >' +
                        '<textarea ref="textarea_descr" placeholder="insert description"/>' +
                        '<button @click="addCategory">Add</button>' +
                        '<button class="modal-default-button" @click="$emit(\'close\')">Close</button>' +
                    '</div>' +
        '       </div>' +
        '</div>' +
        '</transition>',
    methods:{
        addCategory: function(){

            var name = this.$refs.input_name.value
            var description = this.$refs.textarea_descr.value

            var category = {
                name: name,
                description: description,
                count: 0
            }

            console.log(category)
            categoryAPI.save({}, category)
        }
    }

})

new Vue({
    data:{
        showModal: false
    },
    el: '#add_category',
})
