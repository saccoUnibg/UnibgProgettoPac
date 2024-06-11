<template>
    <div align="center">
        <form @submit.prevent="checkInConfirm()">
            <table>
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>CF</th>
                    <th>ID Documento</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="checkin in checkinList" :key="checkin">
                    <td><input type="text" :value="checkin.nome" required/></td>
                    <td><input type="text" :value="checkin.cognome" required/></td>
                    <td><input type="text" :value="checkin.cf" required/></td>
                    <td><input type="text" :value="checkin.id_documento" required/></td>
                </tr>
                </tbody>
            </table>
            <button type="submit">Submit</button>
        </form>
    </div>

    <div align="center">
        <button @click="this.$router.push('/ProfileHomePage')">Homepage</button>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        props: {

        },
        data()  {
            return {
                checkInCount: Number,
                checkinList: [],
            }
        },
        created() {
            console.log("Check In");
            this.checkInCount = this.$route.query.checkInCount;
            for (let i = 0; i < this.checkInCount; i++) {
                this.checkinList.push({
                    nome: '',
                    cognome: '',
                    cf: '',
                    id_documento: '',
                });
            }
            console.log(this.checkInCount);
            console.log(this.checkinList);
        },
        methods: {
            async checkInConfirm() {
                axios.post('http://localhost:8080/prenotazioni/success', this.checkinList)
                .then(response => {
                          console.log(response);
                          this.checkInConfirmed(response.data);
                        })
                .catch(error => {
                    console.error(error);
                });
            },
            checkInConfirmed(response){
                console.log(response);
//                this.$router.push({
//                    name: 'CheckIn',
//                    query: { checkInCount: response.checkinList.length}
//                })
//                .catch(error => console.error(error));
            }
        }
    }
</script>
