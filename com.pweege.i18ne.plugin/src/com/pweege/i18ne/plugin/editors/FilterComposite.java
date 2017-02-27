package com.pweege.i18ne.plugin.editors;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.pweege.i18ne.plugin.model.I18Message;

public class FilterComposite extends Composite {

	private TableViewer viewer;
	
	private List<I18Message> messages = Collections.emptyList();
	
	public FilterComposite(Composite parent) {
		super(parent, SWT.NONE);
		
		this.setLayout(new GridLayout(2, false));
		this.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label filterTabel = new Label(this, SWT.NONE);
		filterTabel.setText("Filter");

		Text filterTextBox = new Text(this, SWT.BORDER);
		GridData gridData =  new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		filterTextBox.setLayoutData(gridData);
		
		this.createTableViewer(this);
		
//		Table table = new Table(this, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
//		
//		table.setHeaderVisible(true);
//		gridData =  new GridData();
//		gridData.horizontalAlignment = SWT.FILL;
//		gridData.verticalAlignment = SWT.FILL;
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.grabExcessVerticalSpace = true;
//		gridData.horizontalSpan = 2;
//		table.setLayoutData(gridData);
//		
//		String[] titles = { "Col 1", "Col 2", "Col 3", "Col 4" };
//		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
//			TableColumn column = new TableColumn(table, SWT.NULL);
//			column.setText(titles[loopIndex]);
//		}
//		
//		for (int loopIndex = 0; loopIndex < 24; loopIndex++) {
//			TableItem item = new TableItem(table, SWT.NULL);
//			item.setText("Item " + loopIndex);
//			item.setText(0, "Item " + loopIndex);
//			item.setText(1, "Yes");
//			item.setText(2, "No");
//			item.setText(3, "A table item");
//		}
//
//		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
//			table.getColumn(loopIndex).pack();
//		}		
	}
	
	
	private void createTableViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        createColumns(parent, viewer);
        
        final Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        viewer.setContentProvider(new ArrayContentProvider());
        //viewer.setInput(ModelProvider.INSTANCE.getPersons());
        
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        viewer.getControl().setLayoutData(gridData);
        
        
        
	}
	
	private void createColumns(Composite parent, TableViewer viewer) {
		String[] titles = { "MsgKey", "Message"};
        int[] bounds = { 100, 100};

        TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
        col.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public String getText(Object element) {
                        I18Message m = (I18Message) element;
                        return m.getKey();
                }
        });
        
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				I18Message m = (I18Message) element;
				return m.getKey();
			}
		});
        
	}
	
	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
	}


}
