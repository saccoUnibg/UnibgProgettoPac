<template>
    <h1>Errore nella registrazione</h1>
</template>

<script>
export default {
    name: 'SingUpFail',
    created() {
      setTimeout(() => {
          this.$router.push("/"); //aspetta 5 secondi poi torna in homepage
      }, 5000);
    }
}
</script>