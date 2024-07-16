<template>
    <div align="center">
        <h1>Prenotazione avvenuta con successo!</h1>
    </div>
</template>

<script>
export default {
    name: 'CheckInSuccess',
    created() {
      setTimeout(() => {
          this.$router.push("/ProfileHomePage"); //aspetta 3 secondi poi torna in homepage
      }, 3000);
    }
}
</script>