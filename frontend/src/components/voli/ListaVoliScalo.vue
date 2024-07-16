<template>
    <table align="center" class="table table-bordered">
        <thead>
        <tr>
            <th>Partenza</th>
            <th>Arrivo</th>
            <th>Ora partenza</th>
            <th>Ora Arrivo</th>
            <th>Compagnia</th>
            <th>Prezzo</th>
            <th>Partenza</th>
            <th>Arrivo</th>
            <th>Ora partenza</th>
            <th>Ora Arrivo</th>
            <th>Compagnia</th>
            <th>Prezzo</th>
            <th>Prezzo Totale</th>
            <th>Prenota</th>
        </tr>
        </thead>
        <tbody>
        <div>
            <tr v-if="typeof listaVoli === 'undefined' || listaVoli.length === 0">
                <td colspan="2"> Nessun volo disponibile</td>
            </tr>
        </div>

        <tr v-for="volo in listaVoli" :key="volo" align="center">
            <td> {{volo.object1.partenza}}</td>
            <td> {{volo.object1.arrivo}}</td>
            <td> {{volo.object1.h_partenza}}</td>
            <td> {{volo.object1.h_arrivo}}</td>
            <td> {{volo.object1.compagnia}}</td>
            <td> {{volo.object1.prezzo}}</td>

            <td> {{volo.object2.partenza}}</td>
            <td> {{volo.object2.arrivo}}</td>
            <td> {{volo.object2.h_partenza}}</td>
            <td> {{volo.object2.h_arrivo}}</td>
            <td> {{volo.object2.compagnia}}</td>
            <td> {{volo.object2.prezzo}}</td>
            <td> {{parseInt(volo.object1.prezzo) + parseInt(volo.object2.prezzo)}}</td>
            <td>
                <div align="center">
                    <form @submit.prevent="prenota(volo)">
                        <button type="submit">Prenota</button>
                    </form>
                </div>
            </td>
        </tr>
        </tbody>
    </table>

    <!--    <div align="center">-->
    <!--        <button @click="this.$router.push('/ProfileHomePage')">Homepage</button>-->
    <!--    </div>-->
</template>

<script>
    export default {
        name: 'ListaVoli',
        props: {
            listaVoli: []
        },
        methods: {
            async prenota(volo) {
                this.axios.post('/prenotazioniScalo/crea', volo)
                .then(response => {
                          console.log(response);
                          this.creaPrenotaSuccess(volo);
                        })
                .catch(error => {
                    console.error(error);
                });
            },
            creaPrenotaSuccess(volo){
                console.log(volo);
                localStorage.setItem('voloDaPrenotare', JSON.stringify(volo));
                this.$router.push({
                    name: 'CreaPrenotazione',
                    query: {scalo: true}
                })
                .catch(error => console.error(error));
            }
        }
    }
</script>
