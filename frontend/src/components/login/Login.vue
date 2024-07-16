<template>
    <div align="center">
        <form @submit.prevent="login"> <!-- previene il submit di default e usa invece il submit nostro -->
            <div class="container">
                <h1>Login</h1>
                <hr>
                <label>Email</label>
                <input type="text" placeholder="mail" name="mail" v-model="form.mail" required>
                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Password" name="psw" id="psw" v-model="form.psw" required>
                <hr>
                <button type="submit">Login</button>
            </div>
        </form>
    </div>
    <h1>{{ message }}</h1>
</template>


<script>
export default {
    name: 'LoginPage',
    data() {
        return {
            message: '',
            form: {
                mail: '',
                psw: ''
            },
            user: Object
        }
    },
    methods: {
        async login() {
            this.message = "Caricamento...";
            this.axios.post('/login', this.form)
            .then(response => {
                      console.log(response);
                      this.message = response.data.nome;
                      this.user = response.data;
                      this.loginSuccess(response.data);
                    })
            .catch(error => {
                console.error(error);
                this.message = "Login fallita";
            });
        },
        loginSuccess(userToPass){
            console.log(userToPass);
            localStorage.setItem('user', JSON.stringify(userToPass));
            this.$router.push({
                name: 'ProfileHomePage'
            })
            .catch(error => console.error(error));
        }
    }
}
</script>