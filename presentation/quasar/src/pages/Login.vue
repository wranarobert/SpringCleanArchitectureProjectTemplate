<template>
  <q-page class="bg-light-blue window-height window-width row justify-center items-center">
    <q-card square bordered>
      <q-card-section>
        <img alt="logo" src="~assets/quasar-logo-full.svg" />
      </q-card-section>
      <q-card-section>
        <login-form class="q-gutter-sm" :loading="loading" @submit="login"></login-form>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script>
import LoginForm from 'components/authentication/LoginForm';

export default {
  computed: {
    loading() {
      return this.$store.getters['authentication/isStatusLoading'];
    },
  },
  methods: {
    login(loginData) {
      this.$store
        .dispatch('authentication/Login', loginData)
        .then(() => this.$router.push('/'))
        .catch(error => error);
    },
  },
  components: {
    LoginForm,
  },
};
</script>

<style></style>
