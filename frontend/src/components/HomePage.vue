<template>
    <div align="center">
        <h1 @click="this.$router.push('/')">Uniflight</h1><br><br>
        <hr>
        <button @click="this.$router.push('/singup')">Registrazione</button>
        <br><br>
        <button @click="this.$router.push('/login')">Login</button>
    </div>
</template>

<script>

    export default {
        name: 'HomePage',
        props: {
            msg: String
        },
        components: {
        },
        methods: {

        }
    }

</script>

<style>
    #nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
     color: #42b983;
    }
  }
}
</style>