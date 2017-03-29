package avagian.gwtApp.client;

import avagian.gwtApp.shared.BusStopInfo;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;

import java.util.List;


public class GwtApp implements EntryPoint {
    public final CellTable<BusStopInfo> table = new CellTable<BusStopInfo>();
    public int pager = 0;
    public int listlenght = 0;
    public VerticalPanel verticalPanel = new VerticalPanel();
    public TextBox enterNumb, enterFstop, enterLstop, eTime;
    public String number, fstop, lstop, etime;
    public Label eNum, eFstop, eLstop, xTime;
    public BusStopInfo newstop = new BusStopInfo("1", "2", "3", "4");
    public Button FindBus;
    public TextBox lookup;
    public String textlookup;
    public Button close;
    public TextBox busNumb;
    public List<BusStopInfo> busStopList;

    VerticalPanel deleteBus = new VerticalPanel();

    public void onModuleLoad() {
        final GwtAppServiceAsync createService = GWT.create(GwtAppService.class);
        final GwtAppServiceAsync fornew = GWT.create(GwtAppService.class);

        final AsyncCallback<List<BusStopInfo>> sorter= new AsyncCallback<List<BusStopInfo>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Sorting Failed");
            }

            @Override
            public void onSuccess(List<BusStopInfo> result) {
               List <BusStopInfo> sortedList = result;
                ListDataProvider<BusStopInfo> dataProvider = new ListDataProvider<BusStopInfo>();
                dataProvider.addDataDisplay(table);
                List<BusStopInfo> list = dataProvider.getList();
                for (BusStopInfo busStopInfo : sortedList ) {
                    list.add(busStopInfo);
                    table.redraw();

                }
            }
        };

        final AsyncCallback<List<BusStopInfo>> callback = new AsyncCallback<List<BusStopInfo>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Something is wrong with transportation, maybe xml file is not found");
            }

            @Override
            public void onSuccess(List<BusStopInfo> result) {
                if (result == null) {
                    Window.alert("Error in .xml file");
                } else {
                    lookup = new TextBox();
                    RootPanel.get().add(lookup);
                    textlookup=lookup.getText();
                    Button filtr = new Button("Find bus", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                           createService.filtration(textlookup, new AsyncCallback<List<BusStopInfo>>() {
                               @Override
                               public void onFailure(Throwable caught) {
                                   Window.alert("filtering failed");
                               }

                               @Override
                               public void onSuccess(List<BusStopInfo> result) {
                                   List <BusStopInfo> sortedList = result;
                                   ListDataProvider<BusStopInfo> dataProvider = new ListDataProvider<BusStopInfo>();
                                   dataProvider.addDataDisplay(table);
                                   List<BusStopInfo> list = dataProvider.getList();
                                   for (BusStopInfo busStopInfo : sortedList ) {
                                       list.add(busStopInfo);
                                       table.redraw();

                                   }
                               }
                           });
                        }
                    });
                    RootPanel.get().add(filtr);
                    busStopList = result;
                    TextColumn<BusStopInfo> busNumberColumn = new TextColumn<BusStopInfo>() {
                        @Override
                        public String getValue(BusStopInfo busStopInfo) {
                            return busStopInfo.getBusNumber();
                        }
                    };
                    busNumberColumn.setSortable(true);
                    TextColumn<BusStopInfo> firstStopColumn = new TextColumn<BusStopInfo>() {
                        @Override
                        public String getValue(BusStopInfo busStopInfo) {
                            return busStopInfo.getFirstStop();
                        }
                    };
                    firstStopColumn.setSortable(true);
                    TextColumn<BusStopInfo> lastStopColumn = new TextColumn<BusStopInfo>() {
                        @Override
                        public String getValue(BusStopInfo busStopInfo) {
                            return busStopInfo.getLastStop();
                        }
                    };
                    lastStopColumn.setSortable(true);
                    TextColumn<BusStopInfo> xTimeColumn = new TextColumn<BusStopInfo>() {
                        @Override
                        public String getValue(BusStopInfo busStopInfo) {
                            return busStopInfo.getxTime();
                        }
                    };
                    xTimeColumn.setSortable(true);
                    ListDataProvider<BusStopInfo> dataProvider = new ListDataProvider<BusStopInfo>();
                    dataProvider.addDataDisplay(table);
                    List<BusStopInfo> list = dataProvider.getList();
                    for (BusStopInfo busStopInfo : busStopList) {
                        list.add(busStopInfo);
                        listlenght += 1;
                    }
                    table.addColumn(busNumberColumn, "Bus Number");
                    table.addColumn(firstStopColumn, "First Stop");
                    table.addColumn(lastStopColumn, "Last Stop");
                    table.addColumn(xTimeColumn, "This bus stop arrival time");
                    table.setVisibleRange(0, 10);
                    table.addColumnStyleName(0, "busNumberColumn");
                    table.addColumnStyleName(0, "firstStopColumn");

                    Button sortByN = new Button("Sort by name", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            createService.sortByBusN(busStopList, sorter);
                        }
                    });
                    sortByN.setStyleName("button");

                    Button sortByF = new Button("Sort by first stop", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            createService.sortByFstop(busStopList, sorter);
                        }
                    });
                    sortByF.setStyleName("button");

                    Button sortByL = new Button("Sort by last stop", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            createService.sortByLstop(busStopList, sorter);
                        }
                    });
                    sortByL.setStyleName("button");
                    Button sortByX = new Button("Sort by arrival time", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            createService.sortByTime(busStopList, sorter);
                        }
                    });
                    sortByX.setStyleName("button");
                    HorizontalPanel sort = new HorizontalPanel();
                    sort.add(sortByN);
                    sort.add(sortByF);
                    sort.add(sortByL);
                    sort.add(sortByX);
                    RootPanel.get("Id").add(sort);
                    RootPanel.get("Id").add(table);

                    Button prev = new Button("Previous page", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            if (pager != 0) {
                                pager -= 10;
                            }
                            table.setVisibleRange(pager, 10);
                        }
                    });

                    Button next = new Button("Next page", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            if ((listlenght > pager+10)){
                                pager += 10;
                                table.setVisibleRange(pager, 10);
                            }


                        }
                    });
                    prev.setStyleName("button");
                    next.setStyleName("button");
                    RootPanel.get("buttons").add(prev);
                    RootPanel.get("buttons").add(next);
                    Button newbus = new Button("Add new Bus", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            verticalPanel.setVisible(true);
                        }
                    });
                    RootPanel.get("new").add(newbus);
                    newbus.setStyleName("button");
                    eNum = new Label("Please enter new bus number:");
                    verticalPanel.add(eNum);
                    enterNumb = new TextBox();
                    verticalPanel.add(enterNumb);
                    number = enterNumb.getText();
                    eFstop = new Label("Please enter first stop:");
                    verticalPanel.add(eFstop);
                    enterFstop = new TextBox();
                    verticalPanel.add(enterFstop);
                    fstop = enterFstop.getText();

                    eLstop = new Label("Please enter last stop:");
                    verticalPanel.add(eLstop);
                    enterLstop = new TextBox();
                    verticalPanel.add(enterLstop);
                    lstop = enterLstop.getText();

                    xTime = new Label("Please enter time:");
                    verticalPanel.add(xTime);
                    eTime = new TextBox();
                    verticalPanel.add(eTime);
                    etime = eTime.getText();



                    Button add = new Button("Add Bus", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            newstop = new BusStopInfo(number, fstop, lstop, etime);
                            final AsyncCallback<Void> adder = new AsyncCallback<Void>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    Window.alert("Something went wrong, bus not added");
                                }

                                @Override
                                public void onSuccess(Void result) {
                                    Window.alert("Bus added ");
                                    ListDataProvider<BusStopInfo> dataProvider = new ListDataProvider<BusStopInfo>();
                                    dataProvider.addDataDisplay(table);
                                    List<BusStopInfo> list = dataProvider.getList();
                                    for (BusStopInfo busStopInfo : busStopList) {
                                        list.add(busStopInfo);
                                        table.redraw();
                                    }
                                }
                            };


                            fornew.addNewBus(newstop, adder);
                        }
                    });

                    close = new Button("Close", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            verticalPanel.setVisible(false);
                        }
                    });
                    HorizontalPanel fornewbus = new HorizontalPanel();
                    fornewbus.add(add);
                    fornewbus.add(close);
                    verticalPanel.add(fornewbus);


                    RootPanel.get("new").add(verticalPanel);
                    verticalPanel.setVisible(false);

                    Button delete = new Button("Delete Bus by number", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            deleteBus.setVisible(true);
                        }
                    });
                    RootPanel.get("new").add(delete);
                    delete.setStyleName("button");
                    Label busNum = new Label("Please enter bus number that you would like to delete");
                    busNumb = new TextBox();
                    deleteBus.add(busNum);
                    deleteBus.add(busNumb);
                    HorizontalPanel fordel = new HorizontalPanel();

                    Button del = new Button("Delete Bus", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            final AsyncCallback<Void> eraser = new AsyncCallback<Void>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    Window.alert("Something went wrong, bus not found");
                                }

                                @Override
                                public void onSuccess(Void result) {
                                    Window.alert("Bus deleted ");
                                    table.redraw();
                                }
                            };
                            fornew.deleteBus(busNumb.getText(), eraser);
                        }
                    });
                    Button closedel = new Button("Close", new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            deleteBus.setVisible(false);
                        }
                    });

                    fordel.add(del);
                    fordel.add(closedel);
                    deleteBus.add(fordel);
                    RootPanel.get("new").add(deleteBus);
                    deleteBus.setVisible(false);
                }
            }
        };


        createService.gwtAppCall("hello", callback);

    }


}