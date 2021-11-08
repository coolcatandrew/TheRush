import React, {useState, useMemo} from "react";
import StatisticDisplay from "./statistic/StatisticDisplay";
import Error from "./Error";
import StatisticAdd from "./statistic/StatisticAdd";

const Results = () => {
    const [statistics, setStatistics] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const [addLoading, setAddLoading] = useState(false);
    const [addError, setAddError] = useState('');

    useMemo(async () => {
        const headers = {'Content-Type': 'application/json'}
        const response = await fetch('/rushing/', {headers});
        if (response.status === 200) {
            setStatistics(await response.json());
            setError('');
        } else {
            setError((response.statusText ? response.statusText : 'Error') + ' Retrieving Results.');
        }
        setLoading(false);

    }, []);

    const onPlayerAdd = async(event) => {
        setAddLoading(true);
        event.preventDefault();
        const player = {
            'name' : event.target.name.value.trim(),
            'team' : event.target.team.value.trim(),
            'position' : event.target.position.value.trim(),
            'rushingStatistic' : {
                'attempts' : event.target.attempts.value,
                'averageAttempts' : event.target.averageAttempts.value,
                'yards' : event.target.yards.value,
                'averageYards' : event.target.averageYards.value,
                'yardsPerGame' : event.target.yardsPerGame.value,
                'touchdowns' : event.target.touchdowns.value,
                'longest' : event.target.longest.value.trim(),
                'firstDowns' : event.target.firstDowns.value,
                'firstDownPercentage' : event.target.firstDownPercentage.value,
                'twentyPlusYards' : event.target.twentyPlusYards.value,
                'fortyPlusYards' : event.target.fortyPlusYards.value,
                'fumbles' : event.target.fumbles.value,
            }
        }
        const response = await fetch('/rushing/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(player)
        });
        if (response.status === 200) {
            const stat = await response.json();
            setStatistics(statistics => [...statistics, stat]);
            setAddError('');
        } else {
            setAddError((response.statusText ? response.statusText : 'Error') + ' Adding a New Player.');
        }
        setAddLoading(false);
    };

    if (error) {
        return <Error message={error}/>;
    }

    return (
        <div>
            <StatisticAdd onPlayerAdd={onPlayerAdd} loading={addLoading} error={addError}/>
            <StatisticDisplay statistics={statistics} loading={loading}/>
        </div>
    );
}

export default Results;