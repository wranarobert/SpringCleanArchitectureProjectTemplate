import Vue from 'vue';

export default function (title, messages) {
  const message = Array.isArray(messages) ? messages.join('\n') : messages;

  Vue.prototype.$q.dialog({
    title,
    message,
    ok: {
      push: true,
      label: 'YES',
      color: 'negative',
    },
  });
}
