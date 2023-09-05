import {
  MantineReactTable,
  useMantineReactTable,
  MRT_ColumnDef,
  MRT_Row,
  MRT_TableInstance,
} from 'mantine-react-table';

interface BaseTableProps<T extends Record<string, any>> {
  data: T[];
  columns: MRT_ColumnDef<T>[];
  renderDetailPanel?: (
    props: {
      row: MRT_Row<T>;
      table: MRT_TableInstance<T>;
    }
  ) => React.ReactNode;
  rowActionMenuItems?: (
    props: {
      row: MRT_Row<T>;
      table: MRT_TableInstance<T>;
    }
  ) => React.ReactNode;
  renderTopToolbarCustomActions?: (
    props: {
      table: MRT_TableInstance<T>;
    }
  ) => React.ReactNode;
  enableEditing?: boolean;
}



const BaseTable = <T extends Record<string, any>>({data, columns, renderDetailPanel, rowActionMenuItems, renderTopToolbarCustomActions, enableEditing}: BaseTableProps<T>) => {

  const table = useMantineReactTable({
    columns,
    data,
    enableColumnFilterModes: true,
    enableColumnOrdering: true,
    enableFacetedValues: true,
    enableGrouping: true,
    enablePinning: true,
    enableRowActions: true,
    enableRowSelection: true,
    enableEditing: enableEditing ? enableEditing : false,
    initialState: { showColumnFilters: false },
    positionToolbarAlertBanner: 'bottom',
    renderDetailPanel: renderDetailPanel ? renderDetailPanel : undefined,
    renderRowActionMenuItems: rowActionMenuItems ? rowActionMenuItems : undefined,
    renderTopToolbarCustomActions: renderTopToolbarCustomActions ? renderTopToolbarCustomActions : undefined,
  });

  return <MantineReactTable table={table} />;
};

export default BaseTable;