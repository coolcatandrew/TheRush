import React from "react";
import {useTable, useFilters, useSortBy, useGlobalFilter, usePagination} from "react-table";
import {Button, Container, Spinner, Table} from "reactstrap";
import StatisticGlobalFilter from "./StatisticGlobalFilter";
import StatisticPagination from "./StatisticPagination";
import Error from "../Error";
import 'font-awesome/css/font-awesome.min.css';
import {faAngleUp, faAngleDown} from '@fortawesome/fontawesome-free-solid'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import 'bootstrap/dist/css/bootstrap.min.css';
import { saveAs } from 'file-saver';

const StatisticTable = ({data, columns, loading = true, error}) => {
    const {
        getTableProps,
        getTableBodyProps,
        headerGroups,
        prepareRow,
        rows,
        state,
        preGlobalFilteredRows,
        setGlobalFilter,
        page,
        canPreviousPage,
        canNextPage,
        pageOptions,
        pageCount,
        gotoPage,
        nextPage,
        previousPage,
        setPageSize,
        state: {pageIndex, pageSize}
    } = useTable(
        {
            columns,
            data,
            initialState: {pageIndex: 0, pageSize: 10},
            disableMultiSort: false,
        },
        useFilters,
        useGlobalFilter,
        useSortBy,
        usePagination
    );

    const onExportCsvClick = async(event) => {
        event.preventDefault();
        const body = rows.map(row => row.original);
        const response = await fetch('/rushing/export', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(body)
        });
        if (response.status === 200) {
            saveAs(await response.blob(), "TheRush.csv");
        }
    };


    if (error && !loading) {
        return <Error message={error}/>;
    }

    if (loading) {
        return(
            <Container fluid className="d-flex justify-content-center p-3">
                <Spinner> Loading...</Spinner> Loading...
            </Container>
        );
    }

    return (
        <div>
            <Container fluid>
                <StatisticGlobalFilter
                    preGlobalFilteredRows={preGlobalFilteredRows}
                    globalFilter={state.globalFilter}
                    setGlobalFilter={setGlobalFilter}
                />
                <div>
                    <Button color='primary' onClick={onExportCsvClick}> EXPORT </Button>
                </div>
                <Table  {...getTableProps()} striped hover className="mt-4">
                    <thead>
                    {headerGroups.map(headerGroup => (
                        <tr {...headerGroup.getHeaderGroupProps()}>
                            {headerGroup.headers.map(column => (
                                <th
                                    {...column.getHeaderProps(column.getSortByToggleProps())}>{column.render("Header")}
                                    <span style={{padding: '5px'}}>
                                        {column.isSorted
                                            ? column.isSortedDesc
                                                ? <FontAwesomeIcon icon={faAngleDown}/>
                                                : <FontAwesomeIcon icon={faAngleUp}/>
                                            : ''}
                                    </span>
                                </th>
                            ))}
                        </tr>
                    ))}
                    </thead>
                    <tbody {...getTableBodyProps()}>
                    {page.map((row, i) => {
                        prepareRow(row);
                        return (
                            <tr {...row.getRowProps()}>
                                {row.cells.map(cell => {
                                    return <td {...cell.getCellProps()}>{cell.render("Cell")}</td>;
                                })}
                            </tr>
                        );
                    })}
                    </tbody>
                </Table>
                <StatisticPagination
                    canPreviousPage={canPreviousPage}
                    canNextPage={canNextPage}
                    pageOptions={pageOptions}
                    pageCount={pageCount}
                    gotoPage={gotoPage}
                    nextPage={nextPage}
                    previousPage={previousPage}
                    setPageSize={setPageSize}
                    pageIndex={pageIndex}
                    pageSize={pageSize}
                />
            </Container>
        </div>
    );

}

export default StatisticTable;
