<template>
  <q-input borderless dense debounce="200" placeholder="Search" :value="value" @input="update">
    <template v-slot:append>
      <q-icon name="search" />
    </template>
  </q-input>
</template>

<script>
import { QInput } from 'quasar';

export default {
  inheritAttrs: false,
  props: {
    value: {
      type: String,
    },
    qAttrs: {
      type: Object,
    },
  },
  methods: {
    update(value) {
      this.$emit('input', value);
    },
  },
  components: {
    QInput,
  },
};
</script>

<style>
</style>
