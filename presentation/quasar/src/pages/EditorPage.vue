<template>
  <q-page padding>
    <div class="document-editor">
      <ckeditor
        :editor="editor"
        v-model="content"
        :config="editorConfig"
        @ready="onReady"
      ></ckeditor>
    </div>
    <q-btn @click="exportPdf">Export</q-btn>

    <q-pdfviewer v-model="show" type="pdfjs" :src="pdfUrl" content-class="absolute" />
  </q-page>
</template>

<script>
import axios from 'axios';
import CKEditor from '@ckeditor/ckeditor5-vue2';
// import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import DecoupledEditor from '@ckeditor/ckeditor5-build-decoupled-document';
// import Base64UploadAdapter from '@ckeditor/ckeditor5-upload/src/adapters/base64uploadadapter';

function ConvertDivAttributes(editor) {
  // Allow <div> elements in the model.
  editor.model.schema.register('div', {
    allowWhere: '$block',
    allowContentOf: '$root',
  });

  // Allow <div> elements in the model to have all attributes.
  editor.model.schema.addAttributeCheck(context => {
    if (context.endsWith('div')) {
      return true;
    }
  });

  // The view-to-model converter converting a view <div> with all its attributes to the model.
  editor.conversion.for('upcast').elementToElement({
    view: 'div',
    model: (viewElement, { writer: modelWriter }) => {
      return modelWriter.createElement('div', viewElement.getAttributes());
    },
  });

  // The model-to-view converter for the <div> element (attributes are converted separately).
  editor.conversion.for('downcast').elementToElement({
    model: 'div',
    view: 'div',
  });

  // The model-to-view converter for <div> attributes.
  // Note that a lower-level, event-based API is used here.
  editor.conversion.for('downcast').add(dispatcher => {
    dispatcher.on('attribute', (evt, data, conversionApi) => {
      // Convert <div> attributes only.
      if (data.item.name != 'div') {
        return;
      }

      const viewWriter = conversionApi.writer;
      const viewDiv = conversionApi.mapper.toViewElement(data.item);

      // In the model-to-view conversion we convert changes.
      // An attribute can be added or removed or changed.
      // The below code handles all 3 cases.
      if (data.attributeNewValue) {
        viewWriter.setAttribute(data.attributeKey, data.attributeNewValue, viewDiv);
      } else {
        viewWriter.removeAttribute(data.attributeKey, viewDiv);
      }
    });
  });
}

export default {
  data() {
    return {
      editor: DecoupledEditor,
      editorConfig: {
        extraPlugins: [ConvertDivAttributes],
      },
      content: '<div class="document-editor__editable-container"></div>',
      reportHead: '',
      pdfUrl: null,
      show: false,
    };
  },
  created() {
    axios
      .post('api/pdf/test')
      .then(({ data }) => data)
      .then(content => {
        this.reportHead = content
          .split('<head>')
          .pop()
          .split('</head>')[0]
          .trim();

        const reportBody = content
          .split('<body>')
          .pop()
          .split('</body>')[0]
          .trim();

        this.content = `<div class="document-editor__editable-container">${reportBody}</div>`;
      });
  },
  methods: {
    onReady(editor) {
      // Insert the toolbar before the editable area.
      editor.ui
        .getEditableElement()
        .parentElement.insertBefore(editor.ui.view.toolbar.element, editor.ui.getEditableElement());
    },
    exportPdf() {
      this.show = false;
      this.pdfUrl = null;

      const fullHTML = `<html><head>${this.reportHead}</head><body>${this.content}</body></html>`;

      console.log(fullHTML);
      axios
        .post('api/pdf/export', fullHTML, {
          headers: { 'Content-Type': 'text/plain' },
          responseType: 'blob',
        })
        .then(({ data }) => data)
        .then(pdf => {
          const blob = new Blob([pdf], { type: pdf.type });

          this.pdfUrl = window.URL.createObjectURL(blob);
          this.show = true;
        });
    },
  },
  components: {
    ckeditor: CKEditor.component,
  },
};
</script>

<style>
.document-editor {
  border: 1px solid var(--ck-color-base-border);
  border-radius: var(--ck-border-radius);

  /* Set vertical boundaries for the document editor. */
  max-height: 700px;

  /* This element is a flex container for easier rendering. */
  display: flex;
  flex-flow: column nowrap;
}

.document-editor .ck-toolbar {
  /* Make sure the toolbar container is always above the editable. */
  z-index: 1;

  /* Create the illusion of the toolbar floating over the editable. */
  box-shadow: 0 0 5px hsla(0, 0%, 0%, 0.2);

  /* Use the CKEditor CSS variables to keep the UI consistent. */
  border-bottom: 1px solid var(--ck-color-toolbar-border);

  /* Adjust the look of the toolbar inside the container. */
  border: 0;
  border-radius: 0;
}

/* Make the editable container look like the inside of a native word processor application. */
.ck-editor__editable {
  padding: calc(2 * var(--ck-spacing-large));
  background: var(--ck-color-base-foreground);

  /* Make it possible to scroll the "page" of the edited content. */
  overflow-y: scroll;
}

.ck-editor__editable .document-editor__editable-container {
  /* Set the dimensions of the "page". */
  width: 210mm;
  min-height: 297mm;

  /* Keep the "page" off the boundaries of the container. */
  padding: 1cm 2cm 2cm;

  border: 1px hsl(0, 0%, 82.7%) solid;
  border-radius: var(--ck-border-radius);
  background: white;

  /* The "page" should cast a slight shadow (3D illusion). */
  box-shadow: 0 0 5px hsla(0, 0%, 0%, 0.1);

  /* Center the "page". */
  margin: 0 auto;
}

/* Adjust the headings dropdown to host some larger heading styles. */
.document-editor .ck-heading-dropdown .ck-list .ck-button__label {
  line-height: calc(1.7 * var(--ck-line-height-base) * var(--ck-font-size-base));
  min-width: 6em;
}

/* Scale down all heading previews because they are way too big to be presented in the UI.
Preserve the relative scale, though. */
.document-editor
  .ck-heading-dropdown
  .ck-list
  .ck-button:not(.ck-heading_paragraph)
  .ck-button__label {
  transform: scale(0.8);
  transform-origin: left;
}

/* Set the styles for "Heading 1". */
.document-editor .ck-content h2,
.document-editor .ck-heading-dropdown .ck-heading_heading1 .ck-button__label {
  font-size: 2.18em;
  font-weight: normal;
}

.document-editor .ck-content h2 {
  line-height: 1.37em;
  padding-top: 0.342em;
  margin-bottom: 0.142em;
}

/* Set the styles for "Heading 2". */
.document-editor .ck-content h3,
.document-editor .ck-heading-dropdown .ck-heading_heading2 .ck-button__label {
  font-size: 1.75em;
  font-weight: normal;
  color: hsl(203, 100%, 50%);
}

.document-editor .ck-heading-dropdown .ck-heading_heading2.ck-on .ck-button__label {
  color: var(--ck-color-list-button-on-text);
}

/* Set the styles for "Heading 2". */
.document-editor .ck-content h3 {
  line-height: 1.86em;
  padding-top: 0.171em;
  margin-bottom: 0.357em;
}

/* Set the styles for "Heading 3". */
.document-editor .ck-content h4,
.document-editor .ck-heading-dropdown .ck-heading_heading3 .ck-button__label {
  font-size: 1.31em;
  font-weight: bold;
}

.document-editor .ck-content h4 {
  line-height: 1.24em;
  padding-top: 0.286em;
  margin-bottom: 0.952em;
}

/* Set the styles for "Paragraph". */
.document-editor .ck-content p {
  font-size: 1em;
  line-height: 1.63em;
  padding-top: 0.5em;
  margin-bottom: 1.13em;
}

/* Make the block quoted text serif with some additional spacing. */
.document-editor .ck-content blockquote {
  font-family: Georgia, serif;
  margin-left: calc(2 * var(--ck-spacing-large));
  margin-right: calc(2 * var(--ck-spacing-large));
}
</style>
