import Vue from 'vue'
import Router from 'vue-router'

// Auth
import Login from './components/Auth/Login.vue'
import Register from './components/Auth/Register.vue'

// User7
import User from './components/User'

Vue.use(Router);

export default new Router({
  routes: [{
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/user',
      name: 'user',
      component: User
    }
  ]
})
