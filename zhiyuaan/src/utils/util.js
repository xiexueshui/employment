// 获取cookie、
export function getCookie (name) {
  var arr = ''
  var reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)')
  // eslint-disable-next-line no-cond-assign
  if (arr = document.cookie.match(reg)) { return (arr[2]) } else { return null }
}

// 设置cookie,增加到vue实例方便全局调用
export function setCookie (key, value) {
  document.cookie = key + '=' + escape(value)
};

// 删除cookie
export function delCookie (name) {
  var exp = new Date()
  exp.setTime(exp.getTime() - 1)
  var cval = getCookie(name)
  if (cval != null) { document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString() }
};
