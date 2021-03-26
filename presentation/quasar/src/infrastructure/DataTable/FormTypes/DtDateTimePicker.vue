<template>
  <q-input dense filled :value="value[field]" @input="update" v-bind="qAttrs">
    <template v-slot:prepend>
      <q-icon name="event" class="cursor-pointer">
        <q-popup-proxy transition-show="scale" transition-hide="scale">
          <q-date :value="value[field]" @input="update($event)" mask="YYYY-MM-DD HH:mm" />
        </q-popup-proxy>
      </q-icon>
    </template>

    <template v-slot:append>
      <q-icon name="access_time" class="cursor-pointer">
        <q-popup-proxy transition-show="scale" transition-hide="scale">
          <q-time :value="value[field]" @input="update($event)" mask="YYYY-MM-DD HH:mm" format24h />
        </q-popup-proxy>
      </q-icon>
    </template>
  </q-input>
</template>

<script>
import {
  QInput, QTime, QDate, QPopupProxy,
} from 'quasar';

export default {
  inheritAttrs: false,
  props: {
    value: {
      type: Object,
      required: true,
    },
    field: {
      type: String,
      required: true,
    },
    qAttrs: {
      type: Object,
    },
  },
  methods: {
    update(value) {
      this.$emit('input', { ...this.value, [this.field]: value });
    },
  },
  components: {
    QInput,
    QTime,
    QDate,
    QPopupProxy,
  },
};
</script>

<style></style>
