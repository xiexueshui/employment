
<template>
<div>
  <slide-verify :l="42"
            :r="10"
            :w="310"
            :h="155"
            @success="onSuccess"
            @fail="onFail"
            @refresh="onRefresh"
            :slider-text="text"
            ></slide-verify>
</div>

</template>

<script>
import Vue from 'vue'
import SlideVerify from 'vue-monoplasty-slide-verify'
Vue.use(SlideVerify)
export default {
  name: 'App',
  data () {
    return {
      msg: '',
      text: '向右滑'
    }
  },
  methods: {
    onSuccess () {
      this.msg = 'login success'
    },
    onFail () {
      this.msg = ''
    },
    onRefresh () {
      this.msg = ''
    }
  }
}

</script>

<style>

</style>
