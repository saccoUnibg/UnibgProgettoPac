<template>
<div align="center">
    <p>Logout effettuato con successo!</p>
</div>
</template>

<script>
export default {
    name: 'LogoutPage',
    created() {
      localStorage.clear();
      setTimeout(() => {
          this.$router.push("/"); //aspetta 2 secondi poi torna in homepage
      }, 2000);
    }
}
</script>