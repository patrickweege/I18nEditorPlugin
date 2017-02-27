package com.pweege.i18ne.plugin.editors;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.pweege.i18ne.plugin.model.I18nMessage;

public class I18nViewerFilter extends ViewerFilter {

	private String searchString;

	public void setSearchText(String s) {
		this.searchString = ".*" + s + ".*";
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length() == 0) {
			return true;
		}

		I18nMessage m = (I18nMessage) element;
		if (m.getKey().matches(searchString)) {
			return true;
		}

		if (m.getKey().matches(searchString)) {
			return true;
		}

		return false;
	}

}
