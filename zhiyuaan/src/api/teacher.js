import Axios from 'axios'
import { getCookie } from '@/utils/util'

export function createcourse (user) {
  Axios.get('/teacher/createcourse', {
    headers: {'token': getCookie('token')},
    params: user
  }).then(function (response) { console.log(response) })
}

export function list (teacherId) {
  Axios.get('/teacher/list', {
    headers: {'token': getCookie('token')},
    params: {teacherId}
  })
}
