package com.pweege.i18ne.plugin.editors;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.pweege.i18ne.plugin.dao.I18neDAO;
import com.pweege.i18ne.plugin.model.I18nMessage;
import com.pweege.i18ne.plugin.model.I18nMessages;

public class FilterComposite extends Composite {

	private TableViewer viewer;
	private I18nViewerFilter viewerFilter;
	private I18nMessages messages;
	private Text filterTextBox;
	
	public FilterComposite(Composite parent) {
		super(parent, SWT.NONE);
		
		this.setLayout(new GridLayout(2, false));
		this.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		this.messages = (new I18neDAO()).getMessages();		

		this.createFilterTextBox(this);
		
		this.createTableViewer(this);
		
	}
	
	private void createFilterTextBox(Composite parent) {
		Label filterTabel = new Label(parent, SWT.NONE);
		filterTabel.setText("Filter");

		this.filterTextBox = new Text(parent, SWT.BORDER);
		this.filterTextBox.addModifyListener(new ModifyListener() {
		    @Override
		    public void modifyText(ModifyEvent event) {
		    	FilterComposite.this.viewerFilter.setSearchText(((Text)event.getSource()).getText());
		    	FilterComposite.this.viewer.refresh();
		    }
		});
		
		GridData gridData =  new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		this.filterTextBox.setLayoutData(gridData);
	}
	
	
	private void createTableViewer(Composite parent) {
		this.viewerFilter = new I18nViewerFilter();
        this.viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        this.viewer.setFilters(this.viewerFilter);
        
        this.createColumns(this.viewer);
        
        final Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        this.viewer.setContentProvider(new ArrayContentProvider());
        this.viewer.setInput(messages.getMessages());
        
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        this.viewer.getControl().setLayoutData(gridData);
        
	}
	
	private void createColumns(TableViewer viewer) {
		String[] titles = { "MsgKey", "Message"};
        int[] bounds = { 100, 100};

        TableViewerColumn col = createTableViewerColumn(viewer, titles[0], bounds[0], 0);
        col.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public String getText(Object element) {
                        I18nMessage m = (I18nMessage) element;
                        return m.getKey();
                }
        });
        
		col = createTableViewerColumn(viewer, titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				I18nMessage m = (I18nMessage) element;
				return m.getKey();
			}
		});
        
	}
	
	private TableViewerColumn createTableViewerColumn(TableViewer tableViewer, String title, int bound, final int colNumber) {
		
        final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
	}


}
