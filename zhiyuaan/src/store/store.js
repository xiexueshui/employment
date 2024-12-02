import vue from 'vue'
import Vuex from 'vuex'

vue.use(Vuex)
const user = new Vuex.Store({
  state: {
    user: localStorage.getItem('user')
  },
  actions: {
    commitUserList: ({commit}, user) => { commit('setUser', user); localStorage.setItem('user', user) },
    commitUserImgurl: ({commit}, imgurl) => { commit('setUserImgurl', imgurl); localStorage.setItem('user', user) }
  },
  mutations: {
    setUser (state, data) {
      state.user = data
    },
    setUserImgurl (state, imgurl) {
      state.user.imgurl = imgurl
    }

  },
  getters: {
    getUser (state) { return state.user }
  }
})
export default user
