<template>
    <div align="center">
        <form @submit.prevent="cerca"> <!-- previene il submit di default e usa invece il submit nostro -->
            <div class="container">
                <h1>Ricerca Voli</h1>
                <hr>
                <label>Aeroporto Partenza</label><br>
                <input type="text" placeholder="Partenza" name="partenza" v-model="form.partenza" size="40"
                       required><br>
                <label>Aeroporto Arrivo</label> <br>
                <input type="text" placeholder="Arrivo" name="arrivo" v-model="form.arrivo" size="40" required><br>
                <label>Data</label> <br>
                <input type="date" placeholder="Data" name="data" v-model="form.data" required>
                <hr>

                <button type="submit" class="registerbtn">Cerca!</button>
            </div>
        </form>
    </div>
    <br>
    <br>
    <br>

    <h1>{{ message }}</h1>

    <ListaVoli v-if="ricerca" :listaVoli="this.listaVoli"/>

    <div align="center">
        <button @click="this.$router.push('/ProfileHomePage')">Homepage</button>
    </div>
</template>

<script>
    import axios from 'axios'
    import ListaVoli from "./ListaVoli"


    export default {
        name: 'RicercaVoli',
        components: {
            ListaVoli
        },
        data() {
            return {
                message: '',
                form: {
                    partenza: '',
                    arrivo: '',
                    data: '',
                    scalo: false
                },
                ricerca: false,
                listaVoli: []
            }
        },
        methods:{
            async cerca(){
                console.log("Cercando");
                this.message = "Ricerca voli in corso..."
                axios.get('http://localhost:8080/voli/lista', {params : this.form})
                .then(response => {
                            this.ricerca = true;
                            console.log(response);
                            this.message = "";
                            this.listaVoli = response.data;
                        })
                .catch(error => {
                    console.error(error);
                    this.message = "Login fallita";
                });
            }
        }
    }
</script>
