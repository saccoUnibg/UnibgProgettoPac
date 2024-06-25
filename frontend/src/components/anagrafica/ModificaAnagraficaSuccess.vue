<template>
    <div align="center">
        <p> Modifica dati anagrafici avvenuta con successo! </p>
    </div>
</template>

<script>
export default {
    name: 'ModificaAnagraficaSuccess',
    created() {
      setTimeout(() => {
          this.$router.push("/ProfileHomePage"); //aspetta 2 secondi poi torna in homepage
      }, 2000);
    }
}
</script>
