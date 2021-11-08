import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Pagination, PaginationItem, PaginationLink} from "reactstrap";

const StatisticPagination = ({
                        canPreviousPage,
                        canNextPage,
                        pageOptions,
                        pageCount,
                        gotoPage,
                        nextPage,
                        previousPage,
                        setPageSize,
                        pageIndex,
                        pageSize
                    }) => {
    const numberOfPages = pageOptions.length;

    if (numberOfPages === 0) {
        return <span/>;
    }

    return (
        <div className="d-flex justify-content-center p-5">
            <Pagination>
                <PaginationItem onClick={() => gotoPage(0)} disabled={!canPreviousPage}>
                    <PaginationLink>{"<<"}</PaginationLink>
                </PaginationItem>
                <PaginationItem onClick={() => previousPage()} disabled={!canPreviousPage}>
                    <PaginationLink>{'<'}</PaginationLink>
                </PaginationItem>
                <PaginationItem>
                    <PaginationLink>
                        Page{' '}
                        <strong>
                            {pageIndex + 1} of {numberOfPages}
                        </strong>{' '}
                    </PaginationLink>
                </PaginationItem>
                <PaginationItem onClick={() => nextPage()} disabled={!canNextPage}>
                    <PaginationLink>{'>'}</PaginationLink>
                </PaginationItem>
                <PaginationItem onClick={() => gotoPage(pageCount - 1)} disabled={!canNextPage}>
                    <PaginationLink>{">>"}</PaginationLink>
                </PaginationItem>
                <PaginationItem>
                    <PaginationLink>
                        <input
                            className="form-control"
                            type="number"
                            defaultValue={pageIndex + 1}
                            onChange={e => {
                                const page = e.target.value ? Number(e.target.value) - 1 : 0
                                gotoPage(page)
                            }}
                            style={{width: '100px', height: '24px'}}
                        />
                    </PaginationLink>
                </PaginationItem>
                {' '}
                <select
                    className="form-control"
                    value={pageSize}
                    onChange={e => {setPageSize(Number(e.target.value))}}
                    style={{width: '120px', height: '38px'}}
                >
                    {[5, 10, 20, 30, 40, 50].map(pageSize => (
                        <option key={pageSize} value={pageSize}>
                            Show {pageSize}
                        </option>
                    ))}
                </select>
            </Pagination>
        </div>
    );
}

export default StatisticPagination;