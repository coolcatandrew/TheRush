import React, {useEffect, useState, useMemo} from "react";
import StatisticTable from "./StatisticTable";
import Error from "../Error";
import {Container, Spinner} from "reactstrap";

const StatisticDisplay = ({statistics, loading}) => {
    const [displayedStatistics, setDisplayedStatistics] = useState(statistics);
    const [statisticHeaders, setStatisticHeaders] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        setDisplayedStatistics(statistics);
    }, [statistics]);

    useMemo(() => {
        const fetchHeaders = async () => {
            const response = await fetch('/rushing/getDataColumns');
            if(response.status === 200) {
                setStatisticHeaders(await response.json());
            } else {
                setError((response.statusText ? response.statusText : 'Error') + ' Getting Table Headers.');
            }
        }
        fetchHeaders();
    }, []);

    if (error && !loading) {
        return <Error message={error}/>;
    }

    if (loading) {
        return(
            <Container fluid className='d-flex justify-content-center p-3'>
                <Spinner> Loading...</Spinner> Loading...
            </Container>
        );
    }

    return (
      <Container fluid>
          {displayedStatistics.length !== 0 &&
            <StatisticTable
                data={displayedStatistics}
                columns={statisticHeaders}
                loading={loading}
                error={error}
            />
          }
      </Container>
    );
}

export default StatisticDisplay;