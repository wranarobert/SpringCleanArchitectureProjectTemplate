<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn flat dense round @click="leftDrawerOpen = !leftDrawerOpen" aria-label="Menu">
          <q-icon name="menu" />
        </q-btn>

        <q-toolbar-title>Quasar App</q-toolbar-title>

        <q-btn @click="handleLogout()">Logout</q-btn>
        <div>Quasar v{{ $q.version }}</div>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" bordered content-class="bg-grey-2">
      <q-list>
        <q-item-label header>Examples</q-item-label>
        <router-link to="/roles">
          <q-item>
            <q-item-section avatar>
              <q-icon name="school" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Roles</q-item-label>
              <q-item-label caption>example</q-item-label>
            </q-item-section>
          </q-item>
        </router-link>

        <router-link to="/users">
          <q-item>
            <q-item-section avatar>
              <q-icon name="code" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Users</q-item-label>
              <q-item-label caption>example</q-item-label>
            </q-item-section>
          </q-item>
        </router-link>
        <router-link to="/upload/test">
          <q-item>
            <q-item-section avatar>
              <q-icon name="chat" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Upload</q-item-label>
              <q-item-label caption>chat.quasar.dev</q-item-label>
            </q-item-section>
          </q-item>
        </router-link>
        <router-link to="/editor">
          <q-item>
            <q-item-section avatar>
              <q-icon name="chat" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Editor</q-item-label>
            </q-item-section>
          </q-item>
        </router-link>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'MyLayout',
  data() {
    return {
      leftDrawerOpen: this.$q.platform.is.desktop,
    };
  },
  methods: {
    handleLogout() {
      this.$store.dispatch('authentication/Logout').then(() => this.$router.push('/login'));
    },
  },
};
</script>

<style></style>
