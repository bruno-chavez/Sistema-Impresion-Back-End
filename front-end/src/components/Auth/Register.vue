<template>
    <div id="register">
        <h1>Register</h1>
        <label>
            <input
                    type="text"
                    name="username"
                    v-model="input.username"
                    placeholder="Username"
                    required
            />
        </label>
        <label>
            <input
                    type="email"
                    name="email"
                    v-model="input.email"
                    placeholder="Email"
                    required
            />
        </label>
        <label>
            <input
                    type="password"
                    name="password"
                    v-model="input.password"
                    placeholder="Password"
                    required
            />
        </label>
        <button type="button" v-on:click="register()">Register</button>
    </div>
</template>

<script>
  import axios from 'axios/index'
  import {env} from '../../mixins/env'

  export default {
    name: 'register',
    mixins: [env],
    data() {
      return {
        input: {
          username: '',
          email: '',
          password: ''
        }
      }
    },
    methods: {
      async register() {
        let res = await axios.post(`${this.backend}/auth/register`, this.input, {withCredentials: true});
        if (res.data.message === 'created') {
          this.$router.push({name: 'login'})
        } else {
          console.log(res.data.message)
        }
      }
    }
  }
</script>
