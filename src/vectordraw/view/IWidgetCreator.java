package vectordraw.view;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;


/**
 * A trait for creating items.

 */
public interface IWidgetCreator {
	default <T> void initComboBox(final ComboBox<T> box, final  Map<T, Image> map, T[] values) {
		ComboBoxFactoryList<T> factory = new ComboBoxFactoryList<>(map);
		box.getItems().addAll(values);
		box.setButtonCell(factory.call(null));
		box.setCellFactory(factory);
	}


	class ComboBoxFactoryList<T> implements Callback<ListView<T>, ListCell<T>> {
		final Map<T, Image> cache;

		public ComboBoxFactoryList(final  Map<T, Image> itemMap) {
			super();
			cache = new HashMap<>();
			cache.putAll(itemMap);
		}

		@Override
		public ListCell<T> call(ListView<T> p) {
			return new ListCell<T>() {
				@Override
				protected void updateItem(T item, boolean empty) {
					super.updateItem(item, empty);
					if(item != null && !empty) {
						if(cache.get(item) == null) System.out.println(item);
						setGraphic(new ImageView(cache.get(item)));
					}
				}
			};
		}
	}
}
