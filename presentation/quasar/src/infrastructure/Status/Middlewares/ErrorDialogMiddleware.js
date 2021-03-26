import QErrorDialog from '../Dialogs/QErrorDialog';
import makeErrorModel from '../Factories/makeErrorModel';

export default function(store) {
  // called when the store is initialized
  store.subscribe(mutation => {
    // called after every mutation.
    // The mutation comes in the format of `{ type, payload }`.
    if (mutation.type.endsWith('statusError')) {
      const error = mutation.payload;

      if (!error) return;

      const errorModel = makeErrorModel(error);

      if (!errorModel) return;

      QErrorDialog(errorModel.title, errorModel.message);
    }
  });
}
