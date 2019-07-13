<template>
    <div id="login">
        <h1>Login</h1>
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
        <button type="button" v-on:click="login()">Login</button>
    </div>
</template>

<script>
  import axios from 'axios/index'
  import { env } from '../../mixins/env'

  export default {
    name: 'login',
    mixins: [env],
    data () {
      return {
        input: {
          email: '',
          password: ''
        }
      }
    },
    methods: {
      async login () {
        let res = await axios.post(`${this.backend}/auth/login`, this.input, { withCredentials: true });
        if (res.data.message === 'authenticated') {
          this.$router.push({ name: 'search' })
        } else {
          console.log(res.data.message)
        }
      }
    }
  }
</script>
