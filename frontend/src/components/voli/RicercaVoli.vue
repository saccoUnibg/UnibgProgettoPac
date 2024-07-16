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
                <input type="date" placeholder="Data" name="data" v-model="form.data" required><br>
                <input type="checkbox" name="scalo" v-model="form.scalo">
                <label >Scalo</label><br>
                <div v-if="form.scalo">
                    <label>Scalo Minimo</label><br>
                    <input type="number" placeholder="Scalo min" v-model="form.scalo_min" size="40" required><br>
                    <label>Scalo Massimo</label><br>
                    <input type="number" placeholder="Scalo Max" v-model="form.scalo_max" size="40" required><br>
                </div>
                <hr>

                <button type="submit" >Cerca!</button>
            </div>
        </form>
    </div>
    <br>
    <br>
    <br>

    <h1>{{ message }}</h1>

    <ListaVoli v-if="ricerca && !form.scalo" :listaVoli="this.listaVoli"/>
    <ListaVoliScalo v-if="ricerca && form.scalo" :listaVoli="this.listaVoli"/>

    <div align="center">
        <button @click="this.$router.push('/ProfileHomePage')">Homepage</button>
    </div>
</template>

<script>
    import axios from 'axios'
    import ListaVoli from "./ListaVoli"
    import ListaVoliScalo from "./ListaVoliScalo"


    export default {
        name: 'RicercaVoli',
        components: {
            ListaVoli,
            ListaVoliScalo
        },
        data() {
            return {
                message: '',
                form: {
                    partenza: '',
                    arrivo: '',
                    data: '',
                    scalo: false,
                    scalo_min: 10,
                    scalo_max: 30
                },
                ricerca: false,
                listaVoli: []
            }
        },
        methods:{
            async cerca(){
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
                });
            }
        }
    }
</script>
