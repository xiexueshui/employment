import Axios from 'axios'

import { getCookie } from '@/utils/util'
export function userUpdate (user) {
  Axios.get('/user/update.do', {
    headers: {'token': getCookie('token')},
    params: {
      'user': user
    }
  }).then(function (response) { console.log(response) })
}
export function register (user) {
  Axios.post('/user/register.do', {
    headers: {'token': getCookie('token')},
    params: user
  }).then(function (response) { console.log(response) })
}
export function getlist () {
  Axios
    .get('/user/getuser.do', {
      headers: { token: getCookie('token') },
      params: { token: getCookie('token') }
    }).then(reponse => {
      console.log(reponse)
      localStorage.setItem('user', reponse.data)
    })
}
