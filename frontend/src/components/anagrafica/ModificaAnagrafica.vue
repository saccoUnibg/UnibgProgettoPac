<template>
    <div align="center">
        <form @submit.prevent="modificaAnagrafica">
            <div class="container">
                <h1>Modifica dati anagrafici</h1>
                <hr>
                <label> Nome </label>
                <input type="text" name="nome" v-model="form.nome" size="15"/>

                <label> Cognome </label>
                <input type="text" name="cognome" v-model="form.cognome" size="15"/>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Password" name="psw" id="psw" v-model="form.psw" required>

                <label for="psw-repeat"><b>Ripeti Password</b></label>
                <input type="password" placeholder="Password" name="psw-repeat" id="psw-repeat" v-model="psw_repeat" required>
                <hr>

                <button type="submit">Effettua modifica</button>
            </div>
        </form>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'ModificaAnagrafica',
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
    created() {
      this.form = JSON.parse(localStorage.getItem('user'));
    },
    methods: {
        async modificaAnagrafica() {
            if (this.form.psw === this.psw_repeat)
            {
                axios.post('http://localhost:8080/anagrafica/modifica', this.form)
                    .then(response => {
                              console.log(response);
                              this.$router.push({
                                      name: 'ModificaAnagraficaSuccess'
                                  })
                              .catch(error => console.error(error));
                            })
                    .catch(error => {
                        console.error(error);
                    });
            }
            else {
                this.message = "I password inseriti non corrispondono";
            }
        }
    }
}
</script>