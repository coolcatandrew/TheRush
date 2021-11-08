import React, {useState} from "react";
import {useAsyncDebounce} from "react-table";

const StatisticGlobalFilter = ({preGlobalFilteredRows, globalFilter, setGlobalFilter}) => {
    const [value, setValue] = useState(globalFilter)
    const count = preGlobalFilteredRows.length
    const onChange = useAsyncDebounce(value => {
        setGlobalFilter(value || undefined)
    }, 200);

    if (count === 0) {
        return <span/>;
    }

    return (
        <span>
            Search:{' '}
            <input
                className="form-control"
                value={value || ""}
                onChange={e => {
                    setValue(e.target.value);
                    onChange(e.target.value);
                }}
                placeholder={`${count} records...`}
            />
        </span>
    );
}

export default StatisticGlobalFilter;
