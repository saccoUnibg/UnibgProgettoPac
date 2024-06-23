<template>
    <div align="center">
        <form @submit.prevent="singup">
            <div class="container">
                <h1>Registrazione</h1>
                <hr>
                <label> Nome </label>
                <input type="text" name="nome" v-model="form.nome" size="15"/>

                <label> Cognome </label>
                <input type="text" name="cognome" v-model="form.cognome" size="15"/>

                <label> Codice fiscale </label>
                <input type="text" name="cf" v-model="form.cf" size="16"/> <br> <br>

                <label><b>Email</b></label>
                <input type="text" placeholder="mail" name="mail" v-model="form.mail" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Password" name="psw" id="psw" v-model="form.psw" required>

                <label for="psw-repeat"><b>Ripeti Password</b></label>
                <input type="password" placeholder="Password" name="psw-repeat" id="psw-repeat" v-model="psw_repeat" required>
                <hr>
                <button type="submit" >Registrati</button>
            </div>
        </form>
    </div>
    <h1>{{ message }}</h1>
</template>

<script>
import axios from 'axios'
export default {
    name: 'SingUpPage',
    data() {
        return {
            message: '',
            form: {
                nome: '',
                cognome: '',
                cf: '',
                mail: '',
                psw: '',
                doc_id: ''
            },
            psw_repeat: '',
        }
    },
    methods: {
        async singup() {
            if (this.form.psw === this.psw_repeat)
            {
                axios.post('http://localhost:8080/registrazioneform', this.form)
                    .then(response => {
                              console.log(response);
                              this.$router.push({
                                      name: 'RegistrazioneSuccess'
                                  })
                              .catch(error => console.error(error));
                            })
                    .catch(error => {
                        console.error(error);
                        this.$router.push({
                            name: 'RegistrazioneFail'
                            })
                        .catch(error => console.error(error));
                    });
            }
            else {
                this.message = "I password inseriti non corrispondono";
            }
        },
    }
}
</script>
